package ggze.loader.scanning;

import ggze.loader.Storage;
import ggze.tool.PropertiesUtils;

public class ScanConfig implements Scan{
    public ScanConfig(){}
    public void scaning() {
        Storage.getObj().setConfig(new PropertiesUtils(Storage.getObj().getConfigPath()).getNodes());
    }
}
