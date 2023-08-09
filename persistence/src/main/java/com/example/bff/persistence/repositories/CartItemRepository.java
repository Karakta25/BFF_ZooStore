package com.example.bff.persistence.repositories;

import com.example.bff.persistence.entities.CartItem;
import com.example.bff.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    Optional<CartItem> findByItemId(UUID userId);
    List<CartItem> findCartItemsByUser(User user);
}
