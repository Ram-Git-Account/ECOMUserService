package EcomUserService.EcomUserService.service;

import EcomUserService.EcomUserService.dto.UserDto;
import EcomUserService.EcomUserService.exception.InvalidCredentialException;
import EcomUserService.EcomUserService.exception.InvalidSessionException;
import EcomUserService.EcomUserService.exception.InvalidTokenException;
import EcomUserService.EcomUserService.exception.UserNotFoundException;
import EcomUserService.EcomUserService.mapper.UserEntityDTOMapper;
import EcomUserService.EcomUserService.model.Session;
import EcomUserService.EcomUserService.model.SessionStatus;
import EcomUserService.EcomUserService.model.User;
import EcomUserService.EcomUserService.repository.SessionRepository;
import EcomUserService.EcomUserService.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.jsonwebtoken.security.MacAlgorithm;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;
@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return UserEntityDTOMapper.getUserDTOFromUserEntity(user);
    }


    public ResponseEntity<List<Session>> getAllSession(){
        List<Session> sessions = sessionRepository.findAll();
        return ResponseEntity.ok(sessions);
    }

    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<UserDto> login(String email, String password) {
        //Get user details from DB
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User for the given email id does not exist");
        }
        User user = userOptional.get();
        //Verify the user password given at the time of login
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialException("Invalid Credentials");
        }
        //token generation
        //String token = RandomStringUtils.randomAlphanumeric(30);
        MacAlgorithm alg = Jwts.SIG.HS256; // HS256 algo added for JWT
        SecretKey key = alg.key().build(); // generating the secret key

        //start adding the claims
        Map<String, Object> jsonForJWT = new HashMap<>();
        jsonForJWT.put("userId", user.getId());
        jsonForJWT.put("roles", user.getRoles());
        jsonForJWT.put("createdAt", new Date());
        jsonForJWT.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

        String token = Jwts.builder()
                .claims(jsonForJWT) // added the claims
                .signWith(key, alg) // added the algo and key
                .compact(); //building the token


        //session creation
        Session session = new Session();
        session.setSessionstatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        session.setLoginAt(new Date());
        sessionRepository.save(session);
        //generating the response
        UserDto userDto = UserEntityDTOMapper.getUserDTOFromUserEntity(user);
        //setting up the headers
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, token);
        return new ResponseEntity<>(userDto, headers, HttpStatus.OK);
    }

    public void logout(String authHeader) {

        String token = authHeader.replace("Bearer ", "").trim();

        Session session = sessionRepository
                .findByToken(token)
                .orElseThrow(() -> new InvalidSessionException("Invalid token"));

        if (session.getSessionstatus() == SessionStatus.ENDED) {
            throw new InvalidSessionException("Session already logged out");
        }

        session.setSessionstatus(SessionStatus.ENDED);
        sessionRepository.save(session);
    }


    public UserDto signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);
    }

    public SessionStatus validate(String authHeader) {

        String token = authHeader.replace("Bearer ", "").trim();

        // 1️⃣ Check session existence + status (your existing logic)
        Session session = sessionRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid token"));

        if (session.getSessionstatus() == SessionStatus.ENDED) {
            throw new InvalidTokenException("Token logged out");
        }

        return SessionStatus.ACTIVE;
    }

}
/*
    MultiValueMapAdapter is map with single key and multiple values
    Headers
    Key     Value
    Token   """
    Accept  application/json, text, images
 */
