/*
 * package com.moviecruiser.userprofile.proxy;
 * 
 * import org.springframework.cloud.openfeign.FeignClient; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody;
 * 
 * import com.moviecruiser.userprofile.model.Register;
 * 
 * 
 * 
 * @FeignClient(value="AuthenticationService", url="http://localhost:8081")
 * public interface UserLoginProxy {
 * 
 * @PostMapping("/saveregister") public ResponseEntity<?> postMovie(@RequestBody
 * Register register);
 * 
 * 
 * }
 */