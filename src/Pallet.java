import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.sqrt;

/**
 * Created by ribeiro.medeiros on 13/03/2017.
 */

public class Pallet {


    public int saturate (int valor){
        if(valor>255){
            return 255;
        }

        if(valor<0){
            return 0;
        }

        return valor;
    }


    public BufferedImage negativo(BufferedImage img){

        int[] pallete64 = {
                0x000000, 0x00AA00, 0x0000AA, 0x00AAAA, 0xAA0000, 0xAA00AA, 0xAAAA00, 0xAAAAAA,
                0x000055, 0x0000FF, 0x00AA55, 0x00AAFF, 0xAA0055, 0xAA00FF, 0xAAAA55, 0xAAAAFF,
                0x005500, 0x0055AA, 0x00FF00, 0x00FFAA, 0xAA5500, 0xAA55AA, 0xAAFF00, 0xAAFFAA,
                0x005555, 0x0055FF, 0x00FF55, 0x00FFFF, 0xAA5555, 0xAA55FF, 0xAAFF55, 0xAAFFFF,
                0x550000, 0x5500AA, 0x55AA00, 0x55AAAA, 0xFF0000, 0xFF00AA, 0xFFAA00, 0xFFAAAA,
                0x550055, 0x5500FF, 0x55AA55, 0x55AAFF, 0xFF0055, 0xFF00FF, 0xFFAA55, 0xFFAAFF,
                0x555500, 0x5555AA, 0x55FF00, 0x55FFAA, 0xFF5500, 0xFF55AA, 0xFFFF00, 0xFFFFAA,
                0x555555, 0x5555FF, 0x55FF55, 0x55FFFF, 0xFF5555, 0xFF55FF, 0xFFFF55, 0xFFFFFF
        };

        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);


        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){

                int cor = img.getRGB(x,y);

                Color pixel = new Color(cor);

                int r = pixel.getRed();
                int g = pixel.getGreen();
                int b = pixel.getBlue();

                Color newPixel;
                int menor = 255+255+255;
                int corfinal = pallete64[0];

                for(int c = 0; c<64;c++){

                    int total;
                    int pcor = pallete64[c];
                    Color paleta = new Color(pcor);

                    int pr = paleta.getRed();
                    int pg = paleta.getGreen();
                    int pb = paleta.getBlue();


                    total = (int) sqrt((r - pr)*(r - pr) + (g - pg)*(g - pg) + (b - pb)*(b - pb));

                    if(total < menor){

                        menor = total;
                        corfinal = c;
                    }
                }

                System.out.println(corfinal);
                out.setRGB(x,y, pallete64[corfinal]);
            }
        }
        return out;
    }

    public void run() throws IOException {
        File PATH = new File("F:\\Aula\\img\\cor");
        BufferedImage arquivo = ImageIO.read(new File(PATH, "turtle.jpg"));
        BufferedImage imgFinal = negativo(arquivo);
        ImageIO.write(imgFinal, "png", new File("64bits.png"));
    }

    public static void main (String[] args) throws IOException {
        Pallet atv1 = new Pallet();
        atv1.run();
    }
}
