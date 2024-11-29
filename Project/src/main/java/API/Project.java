package API;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONArray;

public class Project {
	public static List<ProjectDTO> getMusicalListFromUrl(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();
        List<ProjectDTO> lists = new ArrayList<>();
        Elements elements = doc.select("#list li"); 

        for (Element element : elements) {
            String title = element.select(".tit").text();
            String date = element.select(".date").text();
            String place = element.select(".place").text();
            String imageUrl = element.select(".poster img").attr("src");

            if (!imageUrl.startsWith("http")) {
                imageUrl = "https://m.playdb.co.kr" + imageUrl;
            }

            lists.add(new ProjectDTO(title, date, place, imageUrl));
        }

        return lists;
    }

	 public static JSONArray getMusicalListAsJson(String url) throws Exception {
	        List<ProjectDTO> lists = getMusicalListFromUrl(url);

	        JSONArray jsonArray = new JSONArray();

	        for (ProjectDTO list : lists) {
	            jsonArray.put(list.toJson());
	        }

	        return jsonArray;
	    }

    public static void main(String[] args) {
        try {
            String url = "https://m.playdb.co.kr/Play/List?maincategory=000001";
            JSONArray musicalJsonArray = getMusicalListAsJson(url);
            System.out.println(musicalJsonArray.toString(4)); // Pretty print
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
