package kaptainwutax.seedutils.mc;

import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.util.UnsupportedVersion;
import kaptainwutax.seedutils.util.math.Mth;

public class ChunkRand extends JRand {

	public ChunkRand() {
		super(0);
	}

	public ChunkRand(int seed) {
		super(seed);
	}

	public int setTerrainSeed(int chunkX, int chunkZ, MCVersion version) {
		int seed = chunkX * ((int) 341873128712L) + chunkZ * ((int) 132897987541L);
		this.setSeed(seed);
		return seed;
	}

	public int setPopulationSeed(int worldSeed, int x, int z, MCVersion version) {
		this.setSeed(worldSeed);
		int a, b;

		if(version.isOlderThan(MCVersion.v1_13)) {
			a = this.nextInt() / 2 * 2 + 1;
			b = this.nextInt() / 2 * 2 + 1;
		} else {
			a = this.nextInt() | 1;
			b = this.nextInt() | 1;
		}

		int seed = x * a + z * b ^ worldSeed;
		this.setSeed(seed);
		return seed;
	}

	public int setDecoratorSeed(int populationSeed, int index, int step, MCVersion version) {
		if(version.isOlderThan(MCVersion.v1_13)) {
			throw new UnsupportedVersion(version, "decorator seed");
		}

		int seed = populationSeed + index + (10000 * step);
		this.setSeed(seed);
		return seed;
	}

	public int setDecoratorSeed(int worldSeed, int blockX, int blockZ, int index, int step, MCVersion version) {
		int populationSeed = this.setPopulationSeed(worldSeed, blockX, blockZ, version);
		return this.setDecoratorSeed(populationSeed, index, step, version);
	}

	public int setCarverSeed(int worldSeed, int chunkX, int chunkZ, MCVersion version) {
		this.setSeed(worldSeed);
		int a = this.nextInt();
		int b = this.nextInt();
		int seed = chunkX * a ^ chunkZ * b ^ worldSeed;
		this.setSeed(seed);
		return seed;
	}

	public int setRegionSeed(int worldSeed, int regionX, int regionZ, int salt, MCVersion version) {
		int seed = regionX * (int)341873128712L + regionZ * (int)132897987541L + worldSeed + salt;
		this.setSeed(seed);
		return seed;
	}
}
