package com.demo.flightbooking.mappers;

public interface Mapper<D, E>{

    D mapToDto(E e);

    E mapToEntity(D d);
}
