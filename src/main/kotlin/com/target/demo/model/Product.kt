package com.target.demo.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "products")
data class Product(
    @Schema(
        description = "Product ID generated field for PostgreSQL",
        example = "11"
    )
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Schema(
        description = "Name of the product",
        example = "Wooden Chair"
    )
    val productName: String,
    @Schema(
        description = "General description of the product",
        example = "A sturdy wooden chair"
    )
    val description: String,
    @Schema(
        description = "Material used in the product",
        example = "Wood"
    )
    val material: String,
    @Schema(
        description = "Dimensions of the product",
        example = "40x40x90 cm"
    )
    val dimensions: String,
    @Schema(
        description = "Stock Keeping Unit (SKU) for the product",
        example = "CH123"
    )
    val sku: String,
    @Schema(
        description = "Price of the product",
        example = "79.99"
    )
    val price: Double,
    @Schema(
        description = "Category of the product",
        example = "Furniture"
    )
    val category: String,
    @Schema(
        description = "Relationship with other products",
        example = "Parent"
    )
    val productRelationship: String,
    @Schema(
        description = "Number of items in stock",
        example = "50"
    )
    val inventoryQuantity: Int,
    @Schema(
        description = "URL of the product image",
        example = "https://example.com/chair.jpg"
    )
    val productImageUrl: String,
    @Schema(
        description = "Supplier information for the product",
        example = "ABC Furniture Co."
    )
    val supplierInformation: String,
    @Schema(
        description = "Care instructions for the product",
        example = "Wipe with a dry cloth"
    )
    val careInstructions: String
)