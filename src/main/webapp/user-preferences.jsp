<%
final class MyEnum {

    String getValues(int i){
        switch (i){
            case 0:
                return "sci-fi";
            case 1:
                return "action";
            case 2:
                return "chinese";
            case 3:
                return "kung-fu";
            case 4:
                return "bollywood";
            case 5:
                return "dance";
            case 6:
                return "drama";
            case 7:
                return "children";
            default:
                return "comedey";
        }


    	}
	}

    int max=8;
    int min=0;

    java.util.Random random = new java.util.Random();
    int randomNumber = random.nextInt(max - min) + min;

    MyEnum m = new MyEnum();
    
    response.setContentType("text/plain");

%>

<%=m.getValues(randomNumber)%>