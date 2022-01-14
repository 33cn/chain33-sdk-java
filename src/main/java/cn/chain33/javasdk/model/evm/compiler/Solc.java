package cn.chain33.javasdk.model.evm.compiler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Solc {

    private static final Logger logger = LoggerFactory.getLogger(Solc.class);

    private File solc = null;

    public Solc(String version) {
        try {
            initPropertyBundled();
            if (solc == null || !solc.exists()) {
                initDefaultBundled(version);
            }
        } catch (IOException e) {
            logger.error(" Can't init solc compiler, e: ", e);
            throw new RuntimeException("Can't init solc compiler: ", e);
        }
    }

    private void initPropertyBundled() {
        String propertyName = "solc.path";
        String propertyValue = System.getProperty(propertyName, "");
        if (!"".equals(propertyValue)) {
            logger.info("initBundled from property, propertyName: {}, propertyValue: {}", propertyName, propertyValue);
            solc = new File(propertyValue);
            solc.setExecutable(true);
        }
        return;
    }

    private void initDefaultBundled(String version) throws IOException {
        File tmpDir = new File("/tmp/solidity/" + version);
        if (logger.isTraceEnabled()) {
            logger.trace("tmpDir: {}", tmpDir.getAbsolutePath());
        }

        tmpDir.mkdirs();
        String solcDir = getSolcDir(version);

        try (InputStream is = getClass().getResourceAsStream(solcDir + "file.list");) {
            try (Scanner scanner = new Scanner(is)) {
                while (scanner.hasNext()) {
                    String s = scanner.next();
                    File targetFile = new File(tmpDir, s);

                    try (InputStream fis = getClass().getResourceAsStream(solcDir + s);) {
                        Files.copy(fis, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        if (solc == null) {
                            if (logger.isTraceEnabled()) {
                                logger.trace(" source: {}, destination: {}", solcDir + s, targetFile.getAbsoluteFile());
                            }
                            solc = targetFile;
                            solc.setExecutable(true);
                            break;
                        }
                    }
                }
            }
        }
    }

    private String getSolcDir(String version) {
        return "/solc/" + version + "/" + getOS() + "/";
    }

    private String getOS() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return "win";
        } else if (osName.contains("linux")) {
            return "linux";
        } else if (osName.contains("mac")) {
            return "mac";
        } else {
            throw new RuntimeException("Can't find solc compiler: unrecognized OS: " + osName);
        }
    }

    public File getExecutable() {
        return solc;
    }
}
