```java
package com.legacy.egp.ejb;

import com.legacy.egp.entity.Customer;

import javax.ejb.Remote;
import java.util.List;

/**
 * Customer Bean Remote Interface
 * Provides methods for managing Customer entities remotely.
 */
@Remote
public interface CustomerBeanRemote {

    /**
     * Creates a new Customer.
     *
     * @param customer the customer to create
     * @return the created customer
     */
    Customer createCustomer(Customer customer);

    /**
     * Updates an existing Customer.
     *
     * @param customer the customer to update
     * @return the updated customer
     */
    Customer updateCustomer(Customer customer);

    /**
     * Retrieves a Customer by ID.
     *
     * @param customerId the ID of the customer to retrieve
     * @return the retrieved customer
     */
    Customer getCustomer(Long customerId);

    /**
     * Retrieves a Customer by customer number.
     *
     * @param customerNumber the customer number of the customer to retrieve
     * @return the retrieved customer
     */
    Customer getCustomerByNumber(String customerNumber);

    /**
     * Searches for Customers by last name.
     *
     * @param lastName the last name to search by
     * @return a list of matching customers
     */
    List<Customer> searchCustomersByLastName(String lastName);
}
```