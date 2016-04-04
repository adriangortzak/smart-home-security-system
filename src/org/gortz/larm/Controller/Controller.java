package org.gortz.larm.Controller;
import org.gortz.larm.model.PhpManager;
import org.gortz.larm.model.TellstickManager;

/**
 * Created by adrian on 02/04/16.
 */
public class Controller {
    PhpManager phpManager = new PhpManager();
    TellstickManager tellstick = new TellstickManager();

    //websiteController
    Thread website = new Thread();


    //phpmanager.listenForConnections();
    //tellstick.listenForUnits();
}
