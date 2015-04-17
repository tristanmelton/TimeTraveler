package timeTraveler.core;

import java.io.IOException;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;

public class TimeTravelerAccessTransformer extends AccessTransformer
{
public TimeTravelerAccessTransformer() throws IOException
{
super("TimeTraveler_at.cfg");
}
}