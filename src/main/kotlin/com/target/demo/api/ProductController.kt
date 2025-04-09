package com.target.demo.api

import com.target.demo.model.Product
import com.target.demo.services.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/products")
@Tag(name = "Target Product Repository", description = "API to provide spreadsheet upload and product retrieval")
class ProductController(val service: ProductService) {

    @Operation(
        summary = "Upload product spreadsheet",
        description = """
        Uploads a spreadsheet containing product data and stores it in the database. 
        
        **Expected spreadsheet format (columns in order):**
        - Product Name (String)
        - Description (String)
        - Material (String)
        - Dimensions (String)
        - SKU (String, Unique)
        - Price (Double)
        - Category (String)
        - Product Relationship (String: Parent/Variation)
        - Inventory Quantity (Integer)
        - Product Image URL (String)
        - Supplier Information (String)
        - Care Instructions (String)
        
        **Example Row:**
        | Product Name | Description | Material | Dimensions | SKU  | Price | Category | Product Relationship | Inventory Quantity | Product Image URL | Supplier Information | Care Instructions |
        |-------------|-------------|-----------|-------------|------|--------|-----------|------------------|-----------------|----------------|------------------|------------------|
        | Wooden Chair | A sturdy wooden chair | Wood | 40x40x90 cm | CH123 | 79.99 | Furniture | Parent | 50 | https://example.com/chair.jpg | ABC Furniture Co. | Wipe with a dry cloth |
        
        **File Type:** `.xlsx`
        
        <a href="/products/sample-file">Download a sample file</a>
    """,
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Excel file (.xlsx) containing product data",
            required = true,
            content = [Content(mediaType = "multipart/form-data")]
        ),
        responses = [
            ApiResponse(responseCode = "200", description = "Products uploaded successfully"),
            ApiResponse(responseCode = "400", description = "Invalid file format"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    @PostMapping(consumes = ["multipart/form-data"])
    fun uploadProducts(@RequestParam("file")
                       @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Excel file containing products", required = true)
                       file: MultipartFile): ResponseEntity<String> {
        service.saveProductsFromExcel(file)
        return ResponseEntity.ok("Products uploaded successfully")
    }

    @Operation(
        summary = "Products in postgreSQL",
        description = "all products in the database uploaded via spreadsheet",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Product data",
                content = [Content(schema = Schema(implementation = Product::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Error getting products",
                content = [Content(schema = Schema(implementation = Product::class))]
            )
        ]
    )
    @GetMapping
    fun getAllProducts(): List<Product> = service.getAllProducts()

    @Operation(
        summary = "Delete Products in postgreSQL",
        description = "all products in the database deleted"
    )
    @DeleteMapping
    fun deleteAllProducts() = service.deleteAllProducts()

    @Operation(
        summary = "Products spreadsheet",
        description = "sample products spreadsheet to help callers understand the format",
    )
    @GetMapping("/sample-file")
    fun getSampleFile(): ResponseEntity<Resource> {
        val resource = ClassPathResource("static/sample_products.xlsx")
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"sample_products.xlsx\"")
            .body(resource)
    }
}