package ua.kpi.fpm.patterns;

/**
 * Created by Anastasia Serhienko on 6/22/16.
 */
public class AdapterApp {
    public static void main(String[] args) {
        // 1st approach via inheritance
        VectorGraphicsInterface g1 = new VectorAdapterFromRaster();
        g1.drawLine();
        g1.drawSquare();

        // 2d approach via composition
        VectorGraphicsInterface g2 = new VectorAdapterFromRaster2(new RasterGraphics());
        g2.drawLine();
        g2.drawSquare();
    }
}

interface VectorGraphicsInterface {
    void drawLine();

    void drawSquare();
}

class RasterGraphics {
    void drawRasterLine() {
        System.out.println("Малюємо лінію");
    }

    void drawRasterSquare() {
        System.out.println("Малюємо квадрат");
    }
}

class VectorAdapterFromRaster extends RasterGraphics implements VectorGraphicsInterface {

    @Override
    public void drawLine() {
        drawRasterLine();
    }

    @Override
    public void drawSquare() {
        drawRasterSquare();
    }
}

class VectorAdapterFromRaster2 implements VectorGraphicsInterface {
    RasterGraphics raster = null;//new RasterGraphics();

    public VectorAdapterFromRaster2(RasterGraphics raster) {
        this.raster = raster;
    }

    @Override
    public void drawLine() {
        raster.drawRasterLine();
    }

    @Override
    public void drawSquare() {
        raster.drawRasterSquare();
    }
}