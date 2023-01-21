package com.microserviceDemo.inventory_service.service;

import com.microserviceDemo.inventory_service.dto.InventoryResponse;
import com.microserviceDemo.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    @Autowired
    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("Checking Inventory");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(inventory-> InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build()).toList();
    }
}
