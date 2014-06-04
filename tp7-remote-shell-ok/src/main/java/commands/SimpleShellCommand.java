package commands;

import lombok.extern.slf4j.Slf4j;
import org.crsh.cli.Command;
import org.crsh.cli.Usage;

@Slf4j
public class SimpleShellCommand {

    @Usage("Say Hello")
    @Command
    public String example(String messageToLog) {
        log.info(messageToLog);
        return "Ok";
    }

}