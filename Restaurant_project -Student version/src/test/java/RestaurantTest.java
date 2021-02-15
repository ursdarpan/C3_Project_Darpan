import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setup()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Chips",100);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant mockRestaurant= Mockito.spy(restaurant);
        //setting time inside restaurant hours
        LocalTime mockTime=LocalTime.parse("12:00:00");
        when(mockRestaurant.getCurrentTime()).thenReturn(mockTime);
        boolean result=mockRestaurant.isRestaurantOpen();
        assertTrue(result);
 //       assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant mockRestaurant= Mockito.spy(restaurant);
        //setting current time outside opening hours
        LocalTime mockTime=LocalTime.parse("23:00:00");
        when(mockRestaurant.getCurrentTime()).thenReturn(mockTime);
        boolean result=mockRestaurant.isRestaurantOpen();
        assertFalse(result);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //<<<<<<<<<<Adding Test Case for sum of prices for selected menu Items>>>>>>>>
    /*
    @Test
    public void adding_selected_menu_items_method_should_return_sum_of_Item_prices() {
        //<<<Assuming UI will pass to me selected Item list from the menu shown
        // << Since function is working on item level , adding implementation methods in restaurent class
        restaurant.addToSelectedItemList("Sweet corn soup");
        restaurant.addToSelectedItemList("Chips");

        List<Item> SelectedItems = restaurant.getSelectedItemList();
        Double sum = restaurant.calculateSelectedItemPrice(SelectedItems);

        // sum of chips and Sweet Corn should be 119+100
        assertThat(sum,equalsTo(219.0));
    }

    public void if_no_items_are_in_selected_list_method_should_return_zero() {
        //if no items are selected, then sum should be zero
        List<Item> SelectedItems = Restaurant.getSelectedItemList();
        Double sum = Restaurant.calculateSelectedItemPrice(SelectedItems);

        assertThat(sum,equalsTo(0.0));
    }
   */
}