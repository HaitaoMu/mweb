package com.mweb.common.constats;

public enum PluginType
{
	UNKONW, SAP, PEEP;

	public static PluginType parseType(String type)
	{
		for (PluginType item : PluginType.values())
		{
			if (item.toString().equals(type))
			{
				return item;
			}
		}
		return PluginType.UNKONW;
	}
}
