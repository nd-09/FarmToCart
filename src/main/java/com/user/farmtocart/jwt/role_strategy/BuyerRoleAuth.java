//package com.user.farmtocart.jwt.role_strategy;
//
//import com.user.farmtocart.models.Buyer;
//import com.user.farmtocart.repositories.BuyerRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BuyerRoleAuth implements RoleBasedAuthenticationStrategy{
//    private final BuyerRepository buyerRepository;
//    private PasswordEncoder passwordEncoder;
//    public BuyerRoleAuth(BuyerRepository buyerRepository, PasswordEncoder passwordEncoder) {
//        this.buyerRepository = buyerRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        try{
//            Optional<Buyer> buyer= buyerRepository.findByEmail(email);
//            if(buyer.isPresent()){
//                String password = passwordEncoder.encode(buyer.get().getPassword());
//                String role=buyer.get().getRole();
//                List<GrantedAuthority>grantedAuthorities= Arrays.asList(new SimpleGrantedAuthority(role));
//                return new User(email, password, grantedAuthorities);
//            }
//        }catch (UsernameNotFoundException e){
//            throw new UsernameNotFoundException(e.getMessage());
//        }
//        throw new UsernameNotFoundException(email);
//    }
//}
