package common;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MusicalCrawler {

	
	 public static List<Musical> getMusicalListFromUrl(String url) throws
	 Exception { Document doc = Jsoup.connect(url).get(); List<Musical> musicals =
	 new ArrayList<>(); Elements elements = doc.select("#list li"); 
	 // 적합한 CSS	 셀렉터로 수정
	 
	 for (Element element : elements) { String title =
	 element.select(".tit").text(); String date = element.select(".date").text();
	 String place = element.select(".place").text(); String imageUrl =
	 element.select(".poster img").attr("src"); if (!imageUrl.startsWith("http"))
	 { imageUrl = "https://m.playdb.co.kr" + imageUrl; } musicals.add(new
	 Musical(title, date, place, imageUrl)); }
	 
	 return musicals; }
	 

	public static class Musical {
		private String title;
		private String date;
		private String place;
		private String imageUrl;

		public Musical(String title, String date, String place, String imageUrl) {
			this.title = title;
			this.date = date;
			this.place = place;
			this.imageUrl = imageUrl;

		}

		public String getTitle() {
			return title;
		}

		public String getDate() {
			return date;
		}

		public String getPlace() {
			return place;
		}

		public String getImageUrl() {
			return imageUrl;
		}
	
	}
	public static List<Musical> getMusicalList() throws Exception { String url =
  "https://m.playdb.co.kr/Play/List?maincategory=000001"; Document doc = Jsoup.connect(url).get();
 
  List<Musical> musicals = new ArrayList<>(); Elements elements =
  doc.select("#list li"); // 적합한 CSS 셀렉터로 수정
  System.out.println("Elements size: " + elements.size());
  
  for (Element element : elements) { 
	  String title =  element.select(".tit").text(); // 제목 클래스명 수정 필요 
	  String date =  element.select(".date").text(); 
	  String place =  element.select(".place").text(); 
	  String imageUrl =  element.select(".poster img").attr("src"); // 이미지 URL 클래스명 수정 필요 
  if(!imageUrl.startsWith("http")) { imageUrl = "https://m.playdb.co.kr" +
  imageUrl; } musicals.add(new Musical(title, date, place, imageUrl)); }
  
  return musicals; }

	

}

