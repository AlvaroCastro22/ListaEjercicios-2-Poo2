package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc;


import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.view.HomeView2;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.view.Inicio;

import javax.swing.*;

//Author: Henry Antonio Mendoza Puerta
//Canal: https://www.youtube.com/@hampcode
public class Usil_pre_demo_observer_builder_factory_repository_mvc {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeView2 homeView = new HomeView2();
            homeView.setVisible(true);
        });
    }
}
