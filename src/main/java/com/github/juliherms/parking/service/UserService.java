package com.github.juliherms.parking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.juliherms.parking.model.User;

/**
 * Class responsável por implementar os serviços de usuario
 * @author jlv
 *
 */
@Service
public class UserService {

	private static Map<String, User> userMap = new HashMap<>();

	public List<User> findAll() {
		return userMap.values().stream().collect(Collectors.toList());
	}

	public User findById(String id) {
		return userMap.get(id);
	}

	static {
		String id = getUUID();
		String id1 = getUUID();
		String id2 = getUUID();

		User user = new User(id,1, "Matheus", "Matheus");
		User user1 = new User(id1,2, "Edson", "José Edson");
		User user2 = new User(id2,3, "Juliherms", "Juliherms Lima");

		userMap.put(id, user);
		userMap.put(id1, user1);
		userMap.put(id2, user2);
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
