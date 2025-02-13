//package com.user.farmtocart.jwt.role_strategy;
//
//import com.user.farmtocart.models.Seller;
//import com.user.farmtocart.repositories.SellerRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//public class SellerRoleAuth implements RoleBasedAuthenticationStrategy{
//    private SellerRepository sellerRepository;
//    private PasswordEncoder passwordEncoder;
//    public SellerRoleAuth(SellerRepository sellerRepository, PasswordEncoder passwordEncoder) {
//        this.sellerRepository = sellerRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) {
//        try{
//            Optional<Seller> seller = sellerRepository.findByEmail(email);
//            if(seller.isPresent()){
//                String password = passwordEncoder.encode(seller.get().getPassword());
//                String role= seller.get().getRole();
//                List<GrantedAuthority> grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority(role));
//                return new User(seller.get().getEmail(), password, grantedAuthorities);
//            }
//        }catch(UsernameNotFoundException e){
//            throw new UsernameNotFoundException(e.getMessage());
//        }
//        throw new UsernameNotFoundException("User not found");
//    }
//}
