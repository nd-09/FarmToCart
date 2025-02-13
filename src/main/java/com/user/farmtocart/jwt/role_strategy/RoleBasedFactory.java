//package com.user.farmtocart.jwt.role_strategy;
//
//import com.user.farmtocart.models.AuthenticationRequest;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//public class RoleBasedFactory {
//   public static UserDetailsService getUserDetailsService(AuthenticationRequest authenticationRequest) {
//       switch (authenticationRequest.getRole()) {
//           case"ROLE_BUYER":
//               return null;
//
//           case "ROLE_SELLER":
//               return null;
//           default: return null;
//       }
//   }
//}
