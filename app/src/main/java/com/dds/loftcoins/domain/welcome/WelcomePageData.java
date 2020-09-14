package com.dds.loftcoins.domain.welcome;

import com.dds.loftcoins.R;

public class WelcomePageData {
    public int Image;
    public int Title;
    public int SubTitle;

    public WelcomePageData(int image, int title, int subTitle) {
        Image = image;
        Title = title;
        SubTitle = subTitle;
    }

    public static WelcomePageData[] GetDefaultWelcomePageData(){
        WelcomePageData [] list = new WelcomePageData[3];

        list[0] = new WelcomePageData(R.drawable.welcome_img_1, R.string.welcome_page_title_1, R.string.welcome_page_subtitle_1);
        list[1] = new WelcomePageData(R.drawable.welcome_img_2, R.string.welcome_page_title_2, R.string.welcome_page_subtitle_2);
        list[2] = new WelcomePageData(R.drawable.welcome_img_3, R.string.welcome_page_title_3, R.string.welcome_page_subtitle_3);

        return list;
    }
}
