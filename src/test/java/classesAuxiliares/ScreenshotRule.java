package classesAuxiliares;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ScreenshotRule extends TestWatcher {
//	private AbrirFecharRotinas abrirFecharRotinas = new AbrirFecharRotinas();
	Calendar now = Calendar.getInstance();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy_hh_mm_ss");
	String nomeDocumento = formatter.format(now.getTime());
	String pathScreenshot, imageString;
	int parte1 = 0;
	int parte2 = 0;
	BufferedImage image;
	Robot robot;
	private Screen s = new Screen(); 
	private Pattern m_sincronismo = new Pattern(getImage("imgSincronismo/sincronismo.png")).similar(0.80f);
	private Pattern m_fecharSincronismo = new Pattern(getImage("imgSincronismo/fecharSincronismo.png")).similar(0.97f);
	private Pattern m_popup = new Pattern(getImage("imgGeral/popup.png")).similar(0.90f);

	private String getImage(String path) {
		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	@SuppressWarnings("deprecation")
	@Rule
	public Timeout globalTimeout = new Timeout(1000);

	protected void finished(Description description) {
		String nomePackage = 
				description.getTestClass().getPackage().getName();		

		Assert.assertTrue(true);
		System.out.println("---- Fim ----");
		teardown();
	}

	protected void failed(Throwable e, Description description)
	{
		try {
			String nomeClasse = description.getTestClass().getSimpleName();
			String nomeTeste = description.getMethodName();
			String nomePacote = description.getTestClass().getPackage().getName();	
			tiraScreenshot(nomePacote,nomeClasse, nomeTeste);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}


	public String folderScreenshots(){
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		URL[] urls = ((URLClassLoader)cl).getURLs();
		for(@SuppressWarnings("unused") URL url: urls){
			pathScreenshot = urls[0].getFile();
		}

		parte1 = pathScreenshot.indexOf("");
		parte2 = pathScreenshot.lastIndexOf("/target/");
		pathScreenshot = pathScreenshot.substring(parte1 + 1, parte2);
		urls = null;
		cl = null;
		return pathScreenshot;
	}

	public void tiraScreenshot(String nomePacote,String nomeClasse, String nomeTeste)  
	{
		try {
			//Obtém um screenshot 
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			robot = new Robot();
			image = robot.createScreenCapture(screenRectangle);

			folderScreenshots();
			File diretorio = new File(pathScreenshot+"/"+"target"+"/"+"surefire-reports"+"/"+nomePacote+"."+nomeClasse);
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}

			String nomeArquivoCompleto = diretorio+"\\"+nomeTeste+"."+nomeDocumento+".jpg";
			ImageIO.write(resize(image, 800, 600), "jpg", new File (nomeArquivoCompleto));
			parte1 = nomeArquivoCompleto.indexOf("target");
			nomeArquivoCompleto = nomeArquivoCompleto.substring(parte1);
			System.out.println("[[ATTACHMENT|\\"+nomeArquivoCompleto+"]]");	
			Thread.sleep(2000);
			image.flush();
			screenSize = null;
			screenRectangle = null;
		} 
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro no screenshot!");
		}
	}

	public void teardown() {
		pathScreenshot = null;
		imageString = null;
		image = null;
		robot = null; 
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