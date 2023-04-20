Java Bookstore Project

Requirements:
For raise project Dockerfile call by docker on cmd 


In this project exist following features:

-A user can login:

-Every user have roles

-A user with user role can list all books,can search books with pagination,
can list all books published specific publisher

-Admin and publisher roles users can add book

-Publisher roles users can update books that only belong themself


-FILTR SECTION:

In this project is used my custom filter also is used CriteriaBuilder filter to filter books.
My custom filter prototype url like below:
http://localhost:8080/filtr/1/year:2000&count:500&name:ha


CriteriaBuilder filter prototype link below:

http://localhost:8080/filtr/2/year:2000&count:500&name:ha


Both link search filter dynamicly. So we can filter also one,two or three parametr on our filtr.Just every criteria 
split by '&' sign
http://localhost:8080/filtr/1/year:2000

