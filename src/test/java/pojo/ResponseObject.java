package pojo;

public class ResponseObject {
 float max_temp;
 String valid_date;
 float uv;
 float min_temp;

 public float getMax_temp() {
  return max_temp;
 }
 public float getMin_temp() {
  return min_temp;
 }

 public String getValid_date() {
  return valid_date;
 }

 public float getUv() {
  return uv;
 }

 public ResponseObject(float max_temp, String valid_date, float uv, float min_temp) {
  this.max_temp = max_temp;
  this.valid_date = valid_date;
  this.uv = uv;
  this.min_temp = min_temp;
 }
}
