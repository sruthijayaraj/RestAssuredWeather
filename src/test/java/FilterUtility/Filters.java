package FilterUtility;

import io.restassured.response.Response;
import pojo.ResponseObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Filters {

    Response response;
    List<ResponseObject> responseObjects = new ArrayList<ResponseObject>() ;
    List<ResponseObject> filteredByTemp = new ArrayList<ResponseObject>() ;
    List<ResponseObject> filteredByUv = new ArrayList<ResponseObject>() ;
    List<ResponseObject> finalList = new ArrayList<ResponseObject>() ;

    public Filters(Response response, List<ResponseObject> responseObjects, List<ResponseObject> filteredByTemp) {
        this.response = response;
        this.responseObjects = responseObjects;
        this.filteredByTemp = filteredByTemp;
    }

    public List<ResponseObject> filterDataBasedOnTempLimits(float min_temp, float max_temp){
        ArrayList data= response.jsonPath().get("data");
        System.out.println(data.size());

        for( Object d : data){
            LinkedHashMap<String, Object> li_hash_map =(LinkedHashMap)d;
            System.out.println(li_hash_map.get("valid_date"));
            Object temp = li_hash_map.get("high_temp");
            Object date= li_hash_map.get("valid_date");
            Object uv =li_hash_map.get("uv");
            Object mtemp = li_hash_map.get("min_temp");
            float uvs = Float.valueOf(String.valueOf(uv));
            float temperature =Float.parseFloat(temp.toString());
            float mtemperature =Float.parseFloat(mtemp.toString());

            responseObjects.add(new ResponseObject(temperature,date.toString(),uvs,mtemperature));
        }
        System.out.println("Response objects "+responseObjects.size());

        for (ResponseObject rep : responseObjects){
            if(rep.getMax_temp()<max_temp && rep.getMin_temp() >min_temp)
            {
                filteredByTemp.add(rep);
            }
        }

        System.out.println("count of temp filter "+filteredByTemp.size());

        return filteredByTemp;
    }

    public List<ResponseObject> filterByUV(Integer uv){

        for (ResponseObject resObj : filteredByTemp){
            if(resObj.getUv() <= uv )
            {
                filteredByUv.add(resObj);
            }
        }
        System.out.println("count of filtered by uv "+filteredByUv.size());
        return filteredByUv;
    }

    public LocalDate getDay(String day){
        LocalDate date =  LocalDate.parse(day, DateTimeFormatter.ISO_DATE);
        return date;
    }

    public List<ResponseObject> searchAndFilterBasedOnUserGivenTwoDays(String dayOne, String dayTwo){
        for (ResponseObject resObj : filteredByUv){
            System.out.println(resObj.getValid_date());
            LocalDate lDate = getDay(resObj.getValid_date());
            String dayOfWeek = lDate.getDayOfWeek().toString();
            System.out.println(dayOfWeek);
            if((dayOfWeek.equals(dayOne))||( dayOfWeek.equals(dayTwo)) ){
                finalList.add(resObj);
            }
        }
        return finalList;
    }

}
