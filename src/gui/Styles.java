package gui;

import javafx.geometry.Side;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;

public class Styles {
	private static Image image = new Image("/gui/images/GSLogo.png", 200, 112.5, true, true);
	private static BackgroundSize backgroundSize = new BackgroundSize(200, 112.5, false, false, false, false);
	private static BackgroundPosition bPosition = new BackgroundPosition(Side.LEFT, 100, false, Side.TOP, 23.75, false);
	private static BackgroundImage bGround = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
			bPosition, backgroundSize);
	private static Color COLORON = new Color(0.5843, 0.4902, 0.2471, 1);
	private static Color COLOROFF = new Color(0.5, 0.5, 0.5, 0);
	
	public static final Image GSIcon = new Image("/gui/images/GSIcon.png");
	public static final String BACKGROUNDCOLOR = "-fx-background-color: #041E60";
	public static final String BUTTONSTYLE = "-fx-background-radius: 25px; -fx-border-color: #957D3F ; -fx-border-width: 4.5px; -fx-border-radius: 25px; -fx-background-insets: 2;";
	public static final String BUTTONHOVER = "-fx-base: #957D3F; -fx-background-radius: 25px; -fx-border-color: #FFFFFF ; -fx-border-width: 4.5px; -fx-border-radius: 25px; -fx-background-insets: 2;";
	public static final Background BACKGROUNDIMAGE = new Background(bGround);
	public static final Image HELPICON = new Image("/gui/images/help.png", 25, 25, false, true);

	public static DropShadow SHADOWON = new DropShadow(20, COLORON);
	public static DropShadow SHADOWOFF = new DropShadow(20, COLOROFF);

}
