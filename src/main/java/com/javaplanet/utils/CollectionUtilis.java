package com.javaplanet.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CollectionUtilis {
	
	
	public Set<?> convertListToSet(List<?> list){
		return list.stream().collect(Collectors.toSet());
	}
	
	public List<?> convertSetToList(Set<?> set){
		return set.stream().collect(Collectors.toList());
	}

}
