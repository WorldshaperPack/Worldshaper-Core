package com.deepacat.WorldshaperCore.common.machine.multiblock;

import com.deepacat.WorldshaperCore.api.capability.IParallelMultiblock;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import lombok.Getter;

public class ParallelMultiblock extends WorkableElectricMultiblockMachine implements IParallelMultiblock {

	@Getter
	private final int parallelAmount;

	public ParallelMultiblock(IMachineBlockEntity holder, int parallel) {
		super(holder);
		this.parallelAmount = parallel;
	}
}
