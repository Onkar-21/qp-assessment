/**
 * 
 */
package com.grocerybooking.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * A class defining the user info
 */
public class UserInfoDetails implements UserDetails {

	private static final long serialVersionUID = 4901225792358612391L;

	private String userName;
	
    private String password;
    
    private List<GrantedAuthority> authorities;
    
    public UserInfoDetails(UserInfo userInfo) { 
        userName = userInfo.getUserName();
        password = userInfo.getUserPassword();
        authorities = Arrays.stream(userInfo.getUserRoles().split(",")) 
                .map(SimpleGrantedAuthority::new) 
                .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { 
        return authorities;
    }
    @Override
    public String getPassword() { 
        return password;
    }
    @Override
    public String getUsername() { 
        return userName;
    }
    @Override
    public boolean isAccountNonExpired() { 
        return true;
    }
    @Override
    public boolean isAccountNonLocked() { 
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() { 
        return true;
    }
    @Override
    public boolean isEnabled() { 
        return true;
	}

}
