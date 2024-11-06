
import java.util.List;
import model.Slider;
import model.UserMedia;
import view.SliderDAO;
import view.UserMediaDAO;

public class Main {

    public static void main(String[] args) {
        
        SliderDAO sliderDAO = new SliderDAO();
        List<Slider> sliders = sliderDAO.getSlidersByPage(
                1, 
                5, 
                null, 
                null, 
                null, 
                null);
        int i = sliderDAO.getTotalSliders(null, null);
        System.out.println(i);
        for (Slider slider : sliders) {
            System.out.println(slider);
        }
    }
}
