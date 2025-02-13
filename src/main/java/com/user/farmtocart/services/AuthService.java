package com.user.farmtocart.services;

import com.user.farmtocart.dtos.UserDTO;
import com.user.farmtocart.models.Buyer;
import com.user.farmtocart.models.Seller;
import com.user.farmtocart.repositories.BuyerRepository;
import com.user.farmtocart.repositories.SellerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private  final PasswordEncoder passwordEncoder;

    public AuthService(BuyerRepository buyerRepository, SellerRepository sellerRepository, PasswordEncoder passwordEncoder) {
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("reaching to the service file??"+ email );
        Optional<Buyer> buyer = buyerRepository.findByEmail(email);
        Optional<Seller> seller = sellerRepository.findByEmail(email);

            if(buyer.isPresent()){
                System.out.println("inside buyer?");
                String password = buyer.get().getPassword();
                String role=buyer.get().getRole();
                List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(role));
                return new User(buyer.get().getEmail(), password, grantedAuthorities);
            }else if(seller.isPresent()){
                System.out.println("inside seller?");
                String username = seller.get().getEmail();
                String password = seller.get().getPassword();
                String role=seller.get().getRole();
                List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(role));
                return new User(username, password, grantedAuthorities);
            }
            throw new UsernameNotFoundException("User not found");
    }

    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        String role=userDTO.getRole();
        if(role.equals("BUYER")){
            Buyer buyer=new Buyer();
            buyer.setUsername(userDTO.getName());
            buyer.setPhone(userDTO.getPhone());
            buyer.setAddress(userDTO.getAddress());
            buyer.setGender(userDTO.getGender());
            buyer.setCity(userDTO.getCity());
            buyer.setEmail(userDTO.getEmail());
            buyer.setAge(userDTO.getAge());
            buyer.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            buyerRepository.save(buyer);
        } else if (role.equals("SELLER")) {
            Seller seller=new Seller();
            seller.setUsername(userDTO.getName());
            seller.setPhone(userDTO.getPhone());
            seller.setAddress(userDTO.getAddress());
            seller.setGender(userDTO.getGender());
            seller.setEmail(userDTO.getEmail());
            seller.setAge(userDTO.getAge());
            seller.setCity(userDTO.getCity());
            seller.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            sellerRepository.save(seller);
        }else{
            throw new UsernameNotFoundException("User not saved");
        }

        return ResponseEntity.ok().body(userDTO);
    }
}
