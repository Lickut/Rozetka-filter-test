Filtering notebook page

Narrative:
In order to find needed notebook
As a customer
I want to filter all notebooks different parameters				 

Scenario: Filtering by price
Given notebook filtering page
And all filters are turned off
When Customer filters by $min price and $max price
Then amount of displayed filtered notebooks equals $amount
And all displayed notebooks' cost ranges between $min and $max
And all notebooks with price between $min and $max price are displayed

Examples:     
|min|max|amount|
|4499|5000|5|
|4499|6000|60|

Scenario: Filtering by several parameters
Given notebook filtering page
And all filters are turned off
When Customer includes a filter parameters $parameters with value $values
Then amount of displayed filtered notebooks equals $amount
And all displayed notebooks have values $values in parameters $parameters
And all notebooks with such $parameters and $values are displayed

Examples:     
|parameters|values|amount|
|Класс|Нетбуки|22|
|Производитель|MSI|29|
|Класс,Производитель|Нетбуки,Lenovo|7|
|Класс,Разрешение,Производитель|Геймерские ноутбуки,Full HD,MSI|12|


