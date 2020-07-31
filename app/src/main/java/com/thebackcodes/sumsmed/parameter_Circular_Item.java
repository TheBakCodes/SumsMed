package com.thebackcodes.sumsmed;

public class parameter_Circular_Item {

    private int my_worse_case_min_value;
    private int my_worse_case_max_value;
    private int my_optimal_min_value;
    private int my_optimal_max_value;
    private String my_current_value;
    private String my_name_of_parameter;





    public parameter_Circular_Item(String name_of_parameter, int worse_case_min_value, int worse_case_max_value, int optimal_min_value, int optimal_max_value, String current_value){
        my_worse_case_min_value=worse_case_min_value;
        my_worse_case_max_value=worse_case_max_value;
        my_optimal_min_value=optimal_min_value;
        my_optimal_max_value=optimal_max_value;
        my_current_value=current_value;
        my_name_of_parameter=name_of_parameter;

    }

    public String getMy_current_value() {
        return my_current_value;
    }

    public int getMy_optimal_max_value() {
        return my_optimal_max_value;
    }

    public int getMy_optimal_min_value() {
        return my_optimal_min_value;
    }

    public int getMy_worse_case_max_value() {
        return my_worse_case_max_value;
    }

    public int getMy_worse_case_min_value() {
        return my_worse_case_min_value;
    }

    public String getMy_name_of_parameter() {
        return my_name_of_parameter;
    }

}
