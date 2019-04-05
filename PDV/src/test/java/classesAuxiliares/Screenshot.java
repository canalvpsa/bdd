package classesAuxiliares;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import cucumber.api.Scenario;

public class Screenshot {
	private static Screenshot instancia = new Screenshot();
	Calendar now = Calendar.getInstance();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy_hh_mm_ss");
	String dataHora = formatter.format(now.getTime());
	
	public static Screenshot getInstance(){
		if (instancia ==null){
			instancia = new Screenshot();
		}
		return instancia;
	}

	public void tiraScreenshot(Scenario scenario, String classeTeste) {

		try {
			// Obtem um screenshot
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);

			File diretorio = new File("./"+"target/cucumber-reports/screenshots"+"/");
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}
			
			String nomeArquivo = scenario.getName()+"-"+dataHora+ ".jpg";
			File file;
			ImageIO.write(resize(image, 800, 600), "jpg", file = new File(diretorio+"/"+nomeArquivo));
			Thread.sleep(2000);
			screenSize = null;
			screenRectangle = null;
			
			
			
			byte[] imageInByte;
			BufferedImage originalImage = ImageIO.read(new File(diretorio+"/"+nomeArquivo));

			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();

			// convert byte array back to BufferedImage
			InputStream in = new ByteArrayInputStream(imageInByte);
			BufferedImage bImageFromConvert = ImageIO.read(in);

			ImageIO.write(bImageFromConvert, "jpg", new File(file.getAbsolutePath()));
			
			scenario.embed(imageInByte, "image/png");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro no screenshot!");
		}
	}

	
	private static BufferedImage resize(BufferedImage image, int width, int height) {
		int type = image.getType() == 0 ? BufferedImage.TYPE_INT_RGB : image.getType();
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		g = null;
		return resizedImage;
	}
}
