package com.example.utils;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import java.io.IOException;

public class MyFontUtil {
    public static final Font SIMSUN_H1 = new Font(MyBaseFonts.SIMSUN, 32);
    public static final Font SIMSUN_H2 = new Font(MyBaseFonts.SIMSUN, 24);
    public static final Font SIMSUN_H3 = new Font(MyBaseFonts.SIMSUN, 18);
    public static final Font SIMSUN_TEXT = new Font(MyBaseFonts.SIMSUN, 10.5f);
    public static final Font SIMSUN_REMARK = new Font(MyBaseFonts.SIMSUN, 8);
    public static final Font MSYH_H1 = new Font(MyBaseFonts.MSYH, 32);
    public static final Font MSYH_H2 = new Font(MyBaseFonts.MSYH, 24);
    public static final Font MSYH_H3 = new Font(MyBaseFonts.MSYH, 18);
    public static final Font MSYH_TEXT = new Font(MyBaseFonts.MSYH, 10.5f);
    public static final Font MSYH_REMARK = new Font(MyBaseFonts.MSYH, 8);
    public static final Font MSYHBD_H1 = new Font(MyBaseFonts.MSYHBD, 32);
    public static final Font MSYHBD_H2 = new Font(MyBaseFonts.MSYHBD, 24);
    public static final Font MSYHBD_H3 = new Font(MyBaseFonts.MSYHBD, 18);
    public static final Font MSYHBD_TEXT = new Font(MyBaseFonts.MSYHBD, 10.5f);
    public static final Font MSYHBD_REMARK = new Font(MyBaseFonts.MSYHBD, 8);
    public static final Font MSYHL_H1 = new Font(MyBaseFonts.MSYHL, 32);
    public static final Font MSYHL_H2 = new Font(MyBaseFonts.MSYHL, 24);
    public static final Font MSYHL_H3 = new Font(MyBaseFonts.MSYHL, 18);
    public static final Font MSYHL_TEXT = new Font(MyBaseFonts.MSYHL, 10.5f);
    public static final Font MSYHL_REMARK = new Font(MyBaseFonts.MSYHL, 8);

    public static class MyBaseFonts {
        public static BaseFont SIMSUN;
        public static BaseFont MSYH;
        public static BaseFont MSYHBD;
        public static BaseFont MSYHL;

        static {
            try {
                SIMSUN = BaseFont.createFont("C:\\Windows\\Fonts\\simsun.ttc,0", BaseFont.IDENTITY_H, true);
                // microsoft yahei=msyh
                MSYH = BaseFont.createFont("C:\\Windows\\Fonts\\msyh.ttc,0", BaseFont.IDENTITY_H, true);
                MSYHBD = BaseFont.createFont("C:\\Windows\\Fonts\\msyhbd.ttc,0", BaseFont.IDENTITY_H, true);
                MSYHL = BaseFont.createFont("C:\\Windows\\Fonts\\msyhl.ttc,0", BaseFont.IDENTITY_H, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
