
import java.util.List;
import model.Slider;
import model.UserMedia;
import view.SliderDAO;
import view.UserMediaDAO;

public class Main {

    public static void main(String[] args) {

        SliderDAO sliderDAO = new SliderDAO();
        
        int i = sliderDAO.getTotalSliders(null, null);
        List<Slider> sliders = sliderDAO.getSlidersByPage(
                2,
                5,
                null,
                null,
                null,
                null);
        
        System.out.println(i);
        for (Slider slider : sliders) {
            System.out.println(slider);
        }
    }
}
