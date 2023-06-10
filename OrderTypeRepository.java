package my.edu.utem.ftmk.dad.restorderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.restorderapp.model.OrderType;

import java.util.List;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Long> {

	// 2.1 
	
    List<OrderType> findByNameContaining(String partName);
    
    // 3.1
    
    List<OrderType> findByCodeStartingWith(String partialCode);
    
    
    // 7
    
    List<OrderType> findByOrderByCodeAsc();

    List<OrderType> findByOrderByCodeDesc();
    
    
    
    // 8.1
    
 // Custom query
    @Query(value="select code,name from OrderType where code like '%PU%'", 
    		nativeQuery=true)
    public List<Object[]> selectCustomByCode();
    
    
    
   
     
    // 5.1
   
    List<OrderType> findOrderByNameAsc();
    
   
    
    
   /**
    * 6
    * 
    * From the relationship between the OrderTypeRepository interface and the OrderTypeRESTController class, we can conclude the following:

    1. The OrderTypeRepository interface extends the JpaRepository interface, which provides basic CRUD (Create, Read, Update, Delete) operations for the OrderType entity.
    2. The OrderTypeRESTController class is a REST controller that handles HTTP requests related to order types.
    3. The OrderTypeRESTController class is annotated with @RestController, indicating that it's responsible for processing RESTful requests and returning JSON responses.
    4. The OrderTypeRESTController class defines various methods that map to different endpoints for performing CRUD operations on order types.
    5. The OrderTypeRESTController class utilizes the OrderTypeRepository interface by autowiring it and invoking its methods to perform database operations.
    6. The OrderTypeRepository interface defines additional custom query methods, such as finding order types by name or code, as well as sorting order types by code in ascending or descending order.
    7. The OrderTypeRESTController class maps these custom query methods to appropriate HTTP endpoints, allowing clients to retrieve order type data based on specific criteria or sorted by code.
    8. The OrderTypeRESTController class also includes commented-out code for a custom query method findOrderByNameAsc(), which is not currently implemented in the OrderTypeRepository interface.
    9. Overall, the OrderTypeRepository interface acts as the data access layer, providing methods to interact with the order type entities, while the OrderTypeRESTController class handles the HTTP requests and orchestrates the data retrieval and manipulation operations.
    * 
    */ 
}



