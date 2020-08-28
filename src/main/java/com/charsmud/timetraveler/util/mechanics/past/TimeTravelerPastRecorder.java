package com.charsmud.timetraveler.util.mechanics.past;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TimeTravelerPastRecorder
{
	public PastRecordThread recordThread;
	public List<PastAction> eventsList = Collections.synchronizedList(new ArrayList<PastAction>());
	public String fileName;
}
