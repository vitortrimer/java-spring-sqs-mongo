package com.vitortrimer.anotaaichallenge.domain.products;

import com.vitortrimer.anotaaichallenge.domain.category.Category;

public record ProductDTO(String id, String title, String description, String ownerId, Integer price, String categoryId) {
}