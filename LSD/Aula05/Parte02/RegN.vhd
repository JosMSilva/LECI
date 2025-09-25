library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;


entity RegN is
	generic (N       : positive := 8);
	port 	  (reset   : in std_logic; 
				enable  : in std_logic;
				clock   : in std_logic;
				dataIn  : in  std_logic_vector((N-1) downto 0);
				dataOut : out std_logic_vector((N-1) downto 0));
end RegN;

architecture Behavioral of RegN is
begin
	process(clock, reset)
	begin
		if (reset = '1') then
			dataOut <= (others => '0');
		elsif(rising_edge(clock)) then
			if (enable = '1') then
				dataout <= datain;
			end if;
		end if;
	end process;
end Behavioral;