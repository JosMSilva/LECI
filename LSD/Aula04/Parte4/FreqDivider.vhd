library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity FreqDivider is
	GENERIC(k : POSITIVE := 1000000);
	port (clkIn : in std_logic;
			clkOut : out std_logic);
end FreqDivider;

architecture Behavioral of FreqDivider is
	signal s_counter : INTEGER := 0;
	signal s_halfWay : INTEGER := 0;
begin
s_halfWay <= (k / 2);
process(clkIn)
begin
	if (rising_edge(clkIn)) then
		if (s_counter = s_halfway) then
			clkout <= '1';
		end if;
		if (s_counter = k-1) then
			s_counter <= 0;
			clkout <= '0';
		end if;
		s_counter <= s_counter + 1;
	end if;
end process;
end Behavioral;