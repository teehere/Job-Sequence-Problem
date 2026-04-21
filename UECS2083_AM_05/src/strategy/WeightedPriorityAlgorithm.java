package strategy;

import java.util.*;
import model.Delivery;

public class WeightedPriorityAlgorithm extends AbstractDeliveryStrategy<Delivery> {

    public WeightedPriorityAlgorithm(List<Delivery> delivery) {
        super(delivery); 
    }

    @Override
    public void schedule(List<Delivery> delivery) {

        // clear previous results 
        selected.clear();
        unselected.clear();
        totalProfit = 0;
        
        // check if the input list is valid
        if (delivery == null || delivery.isEmpty()) {
            return;
        }

        // priority queue sorted by highest profit 
        PriorityQueue<Delivery> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(b.getProfit(), a.getProfit())
        );

        // add all deliveries into the priority queue
        pq.addAll(delivery);
        
        int order = 1; // track execution order

        // process deliveries based on highest priority 
        while (!pq.isEmpty()) {
            Delivery d = pq.poll();

            // display order and delivery details
            System.out.println(order + ". " + d.getId() 
                    + " | Profit: " + d.getProfit()
                    + " | Deadline: " + d.getDeadline());

            selected.add(d); // mark job as selected
            totalProfit += d.getProfit(); // update total profit
            
            order++; 
        }

        // find jobs that were unselected
        for (Delivery job : delivery) {
            if (!selected.contains(job)) {
                unselected.add(job);
            }
        }
    }
}
