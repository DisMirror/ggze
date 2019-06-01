package loader.scanning;

import loader.Storage;
import tool.PropertiesUtils;

public class ScanConfig implements Scan{
    public ScanConfig(){}
    public void scaning() {
        Storage.getObj().setConfig(new PropertiesUtils(Storage.getObj().getConfigPath()).getNodes());
    }
}
