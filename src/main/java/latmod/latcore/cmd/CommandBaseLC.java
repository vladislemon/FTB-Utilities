package latmod.latcore.cmd;

import latmod.core.cmd.CommandLM;
import latmod.latcore.LC;
import net.minecraft.command.*;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public abstract class CommandBaseLC extends CommandLM
{
	public static void registerCommands(FMLServerStartingEvent e)
	{
		regCmd(e, new CmdLatCore(LC.mod.config().commands.latcore));
		regCmd(e, new CmdSetNick(LC.mod.config().commands.setnick));
		regCmd(e, new CmdRealNick(LC.mod.config().commands.setnick));
		regCmd(e, new CmdSetSkin(LC.mod.config().commands.setskin));
		regCmd(e, new CmdSetCape(LC.mod.config().commands.setcape));
	}
	
	private static void regCmd(FMLServerStartingEvent e, CommandBaseLC c)
	{ if(c.enabled > 0) e.registerServerCommand(c); }
	
	public final int enabled;
	
	public CommandBaseLC(String s, int e)
	{ super(s); enabled = e; }
	
	public final int getRequiredPermissionLevel()
	{ return (enabled == 2) ? 2 : ((enabled == 1) ? 0 : 5); }
	
	public final boolean canCommandSenderUseCommand(ICommandSender ics)
	{ return enabled > 0; }
}