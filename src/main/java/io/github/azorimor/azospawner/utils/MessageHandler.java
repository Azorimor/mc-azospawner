package io.github.azorimor.azospawner.utils;

import io.github.azorimor.azospawner.files.PluginFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MessageHandler  {

    private String prefix;

    private PluginFile pluginFile;


    private String noPlayer;
    private String noPermission;
    private String wrongCommandUsage;
    private String noNumber;
    private String commandGiveSpawner;
    private String noEntityType;

    public MessageHandler(PluginFile pluginFile) {
        this.pluginFile = pluginFile;

        loadDefaults();
    }

    private void loadDefaults(){
        this.prefix = translateColorCodes(pluginFile.getString("prefix"));

        this.noPlayer = translateColorCodes(pluginFile.getString("command.message.noPlayer"));
        this.noPermission = translateColorCodes(pluginFile.getString("command.message.noPermission"));
        this.wrongCommandUsage = translateColorCodes(pluginFile.getString("command.message.wrongCommandUsage"));
        this.noNumber = translateColorCodes(pluginFile.getString("command.message.noNumber"));
        this.commandGiveSpawner = translateColorCodes(pluginFile.getString("command.message.giveSpawner"));
        this.noEntityType = translateColorCodes(pluginFile.getString("command.message.noEntityType"));
    }

    /**
     * Translates the alternative Color Codes from spigot/minecraft. So the <code>'&'</code> are replaced, so
     * the messaage will be colored.
     * @param string Message, which should be translated
     * @return translated Message
     */
    public String translateColorCodes(String string){
        return string.replace('&','§');
    }

    /**
     * Sends the {@link CommandSender} the message, which contains, that he must be a
     * {@link Player} to perform this action.
     * The message is loaded from the configuration file.
     * @param sender {@link CommandSender} which should recieve the message.
     */
    public void sendNoPlayer(CommandSender sender){
        sender.sendMessage(prefix + noPlayer);
    }

    /**
     * Sends a {@link CommandSender} the message, that he does not have the permission
     * to perform a specific {@link Command}.
     * The message is loaded from the configuration file.
     * @param sender {@link CommandSender} which should recieve the message.
     * @param command The {@link Command} the {@link CommandSender} does not have enought
     *                permissions for.
     */
    public void sendNoPermission(CommandSender sender, Command command){
        sender.sendMessage(prefix + noPermission.replace("%command%",command.getName()));
    }

    /**
     * Sends a {@link CommandSender} the message, that he did not use a {@link Command} the right way.
     * The message is loaded from the configuration file.
     * @param sender {@link CommandSender} which should recieve the message.
     * @param command {@link Command} which was used the wrong way.
     */
    public void sendWrongCommandUsage(CommandSender sender, Command command){
        sender.sendMessage(prefix + wrongCommandUsage.replace("%command%",command.getName()).replace("%usage%",command.getUsage()));
    }

    //TODO comment
    public void sendPluginMessage(CommandSender sender, String message){
        sender.sendMessage(translateColorCodes(message));
    }

    /**
     * Sends a {@link CommandSender} the message, that he need to use a number as a specifig argument in a {@link Command}
     * @param sender {@link CommandSender} which should recieve the message.
     * @param wrongArgument The wrong Argument, which needs to be a number. This values is used as feedback to the {@link CommandSender}
     */
    public void sendNoNumber(CommandSender sender, String wrongArgument){
        sender.sendMessage(prefix+ noNumber.replace("%wrongArgument%",wrongArgument));
    }

    //TODO comment
    public void sendCommandGiveSpawnerSuccess(Player player, EntityType entityType, int amount){
        player.sendMessage(prefix+commandGiveSpawner.replace("%type%",entityType.toString()).replace("%amount%",String.valueOf(amount)));
    }

    public void sendNoEntityType(CommandSender commandSender, String noEntityType){
        commandSender.sendMessage(prefix+ noEntityType.replace("%wrongType%",noEntityType));
        //TODO Ausgabe aller Möglichkeiten.
    }

    public void reloadValues(){
        //TODO add more functionallity.
        loadDefaults();
    }
}