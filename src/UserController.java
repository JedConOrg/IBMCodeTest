@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // You can perform authentication logic here
        if ("yourUsername".equals(user.getUsername()) && "yourPassword".equals(user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

}
