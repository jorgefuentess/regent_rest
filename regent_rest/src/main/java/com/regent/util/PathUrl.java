package com.regent.util;


import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class PathUrl {
	  public static Boolean tienePermiso(Authentication authentication, String path) {
	    String path2 = path.replace("/registro", "").replace("/html", "").replace("{\"path\":\"", "").replaceAll("\"}", "");
	    UserDetails userDetails = (UserDetails)authentication.getPrincipal();
	    Collection<String> auth = AuthorityUtils.authorityListToSet(userDetails.getAuthorities());
	    return Boolean.valueOf(auth.contains(path2));
	  }
}
