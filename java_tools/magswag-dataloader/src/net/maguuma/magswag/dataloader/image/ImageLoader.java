package net.maguuma.magswag.dataloader.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {
  private static final BufferedImage FALLBACK;

  static {
    FALLBACK = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = FALLBACK.createGraphics();
    g.setColor(Color.RED);
    g.fillRect(0, 0, 16, 16);
  }

  public static BufferedImage loadImage(String filename) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(filename));
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (img == null) {
      img = FALLBACK;
    }
    return img;
  }

  public static BufferedImage loadImage(String filename, int width, int height) {
    BufferedImage img = loadImage(filename);
    BufferedImage scaledImg = null;
    if (img != null) {
      scaledImg = new BufferedImage(width, height, img.getType());
      Graphics2D g = scaledImg.createGraphics();
      AffineTransform at = AffineTransform.getScaleInstance(0, 0);
      g.drawRenderedImage(img, at);
    }
    return scaledImg;
  }
}
