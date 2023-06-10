package my.edu.utem.ftmk.dad.restorderapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.edu.utem.ftmk.dad.restorderapp.model.OrderType;
import my.edu.utem.ftmk.dad.restorderapp.repository.OrderTypeRepository;

@RestController
@RequestMapping("/api/ordertypes")
public class OrderTypeRESTController {

    @Autowired
	private OrderTypeRepository orderTypeRepository;
	
    @DeleteMapping ("{orderTypeId}")
	public ResponseEntity<HttpStatus> deleteOrderType(@PathVariable long orderTypeId) 
    {
    	orderTypeRepository.deleteById(orderTypeId);

    	return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public List<OrderType> getOrderTypes() 
	 {
		 return orderTypeRepository.findAll();
	 }

	 @GetMapping ("{orderTypeId}")
	public OrderType getOrderType(@PathVariable long orderTypeId) 
	 {
		 
		 OrderType orderType=orderTypeRepository.findById(orderTypeId).get();
		
		 return orderType;
	}

	 @PostMapping( )
	public OrderType insertOrderType(@RequestBody OrderType orderType)
	{
		 return orderTypeRepository.save(orderType);
	}

	 @PutMapping ( )
	public OrderType updateOrderType(@RequestBody OrderType orderType) 
	{
		 return orderTypeRepository.save(orderType);
	}
	 
	 
    // 2.2 and 2.3
	 
	/**
	 * 
	 * This method find order type data according to the order type's name.
	 * 
	 * @param orderType
	 * @return A list of order types record.
	 */
	 @GetMapping("/find/name/{name}")
	 public List<OrderType> findOrderType(@PathVariable String name) {
	     List<OrderType> orderTypes = orderTypeRepository.findByNameContaining(name);
	     return orderTypes;
	 }
	 
	 
	 // 3.2 and 3.3
	 
	 /**
	  * Find order types by code.
	  *
	  * @param code The partial code to search for.
	  * @return A list of order types matching the provided partial code.
	  */
	 @GetMapping("/find/code/{code}")
	 public List<OrderType> findOrderType2(@PathVariable("code") String code) {
	     return orderTypeRepository.findByCodeStartingWith(code);
	 }
	 
	 
	 // 4
	 
	 /**
	  * Count the number of order types.
	  *
	  * @return The count of order types in the repository.
	  */
	 @GetMapping("/count")
	 public long countOrderTypes() {
	     return orderTypeRepository.count();
	 }
	 
	 
	 // 7
	 
	 
	 /**
	  * Get a sorted list of order types by code in ascending order.
	  *
	  * @return A sorted list of order types by code in ascending order.
	  */
	  @GetMapping("/sort/asc")
	  public List<OrderType> getSortedOrderTypesByCodeAsc() {
	      return orderTypeRepository.findByOrderByCodeAsc();
	  }

	  /**
	  * Get a sorted list of order types by code in descending order.
	  *
	  * @return A sorted list of order types by code in descending order.
	  */
	  @GetMapping("/sort/desc")
	  public List<OrderType> getSortedOrderTypesByCodeDesc() {
	      return orderTypeRepository.findByOrderByCodeDesc();
	  }
	    
	    
	    
	    // 8.2 and 8.3
	    
	    /**
	     * This method demonstrate the invocation of custom query and return the
	     * result in Object form.
	     * 
	     * @return A list of objects where value of each field in separated arrays
	     */
	    @GetMapping("/find/pickup/raw")
	    public List<Object[]> getRawPickUpOderCode() {

	        // Execute query method
	        List<Object[]> objOrderTypes = orderTypeRepository.selectCustomByCode();

	        // For debugging purpose
	        for (Object[] objOrderType:objOrderTypes) {

	            for (Object currentObject: objOrderType) {

	                System.out.println(currentObject.toString());
	            }
	        }
	        return objOrderTypes;
	    }
	    
	    
	    
	    // 8.4 and 8.5
	    
	    /**
	     * This method demonstrate the invocation of custom query 
	     * 
	     * 
	     * @return A list of objects where result of query execution wrap in
	     * OrderType
	     */
	    @GetMapping("/find/pickup/wrap")
	    public List<OrderType> getWrapPickUpOderCode() {

	    	// Execute query method
	    	List<Object[]>objOrderTypes = orderTypeRepository.selectCustomByCode();

	    	// Wrap result in a list of order type
	    	List<OrderType> orderTypes = new ArrayList<OrderType>();
	    	for (Object[] objOrderType:objOrderTypes) {

	    	    // Wrap in order type
	    	    OrderType orderType = new OrderType();
	    	    orderType.setCode(objOrderType[0].toString());
	    	    orderType.setName(objOrderType[1].toString());
  
	    	    // Add into list
	    	    orderTypes.add(orderType);

	    	}

	    	return orderTypes;

	    	}
	    
	    
	     
	     // 5.2  and 5.3
	      
	    @GetMapping("/sort/name/asc")
	    public List<OrderType> getSortedOrderTypesByNameAsc() {
	        return orderTypeRepository.findOrderByNameAsc();
	    }
	    
	    
	  
}